---
sessionId: session-261112-162214-1wno
---

# Requirements

### Overview & Goals
Migrera AIAgent från `/api/v1/chat/completions` till `/api/v1/responses` API:t hos OpenRouter. Det nya API:t är mer modernt och OpenAI-kompatibelt, med ett strukturerat `input`/`output` format istället för `messages`.

### Scope
**In Scope:**
- Ändra API-endpoint från `/api/v1/chat/completions` till `/api/v1/responses`
- Uppdatera request-formatet från `messages` till `input` array med content parts
- Uppdatera response-parsing från `choices` till `output` array
- Hantera `function_call` och `function_call_output` items i stället för `tool_calls`
- Behålla samma 4 verktyg: `get_current_datetime`, `view_file`, `create_file`, `list_directory`
- Behålla samma användarinteraktion och bekräftelseslinga

**Out of Scope:**
- Streaming (behålls ej, den nuvarande koden använder inte streaming)
- Reasoning-funktioner
- Web search
- Multimodal content

### Key Differences Between APIs

| Aspekt | Chat Completions | Responses |
|--------|-----------------|-----------|
| Endpoint | `/api/v1/chat/completions` | `/api/v1/responses` |
| Inmatning | `messages` array | `input` array med content parts |
| System message | `role: "system"` i messages | `instructions` fält |
| Verktyg i request | `tools` array | `tools` array (samma format) |
| Svar | `choices[].message` | `output` array med items |
| Text svar | `message.content` | `message.content[].output_text.text` |
| Tool calls | `message.tool_calls[]` | `function_call` items i output |
| Tool resultat | `role: "tool"` message | `function_call_output` item |
| Finish reason | `choices[].finish_reason` | Ingen – kolla item-typer i output |

# Technical Design

### Current Implementation
`AIAgent.java` (222 rader) i `com.example.java26.agent`:
- Använder `HttpClient` för HTTP-requests
- `ObjectMapper` (tools.jackson) för JSON serialisering/deserialisering
- `Request` record med `model`, `messages`, `tools`
- `Message` record med `role`, `content`, `tool_call_id`, `tool_calls`
- `OpenRouterResponse` med `choices`, varje `Choice` har `message` och `finish_reason`
- Slinga: skicka request → kolla `finish_reason` → om `tool_calls` kör verktyg och lägg till resultat, om `stop` bryt

### Proposed Changes

#### 1. Ny Request-struktur
- `Request` record: `model`, `instructions` (string), `input` (List<InputItem>), `tools`
- `InputItem` blir en union av olika typer:
  - `MessageItem`: `type="message"`, `role`, `content` (List<ContentPart>)
  - `FunctionCallItem`: `type="function_call"`, `id`, `call_id`, `name`, `arguments`
  - `FunctionCallOutputItem`: `type="function_call_output"`, `call_id`, `output`
- `ContentPart`: `type="input_text"`, `text`

#### 2. Ny Response-struktur
- `Response` record: `id`, `output` (List<OutputItem>), `usage`
- `OutputItem` union:
  - `MessageOutput`: `type="message"`, `role`, `content` (List<OutputContentPart>)
  - `FunctionCallOutput`: `type="function_call"`, `id`, `call_id`, `name`, `arguments`
- `OutputContentPart`: `type="output_text"`, `text`

#### 3. Uppdaterad huvudslinga
- Bygga `input` array från konversationshistorik
- Extrahera `instructions` (system message) separat
- Skicka till `/api/v1/responses`
- Parsa `output` array: leta efter `function_call` items för verktygsanrop, `message` items för text
- Vid verktygsanrop: lägga till både `function_call` item OCH `function_call_output` item i input
- Vid text: extrahera från `message.content[].output_text.text`

#### 4. Verktygsdefinitioner
- Behåll samma struktur, `tools` array ser likadan ut i båda API:n
- `Tool` record med `type="function"` och `function` (namn, beskrivning, parameters)

### Data Models

**Request:**
```java
record Request(String model, String instructions, List<InputItem> input, List<Tool> tools)
record InputItem(String type, String role, List<ContentPart> content, // message
                 String id, String call_id, String name, String arguments, // function_call
                 String output) // function_call_output
record ContentPart(String type, String text)
```

**Response:**
```java
record Response(String id, List<OutputItem> output, Usage usage)
record OutputItem(String type, String id, String role, String status, 
                  List<OutputContentPart> content, // message
                  String call_id, String name, String arguments) // function_call
record OutputContentPart(String type, String text)
record Usage(int input_tokens, int output_tokens, int total_tokens)
```

### Architecture Diagram

``` mermaid
graph LR
    A[Användarinmatning] --> B[Bygg InputItem-array]
    B --> C[Skicka till /api/v1/responses]
    C --> D[Parsa Output-array]
    D --> E{Typ av item?}
    E -->|function_call| F[Kör verktyg]
    F --> G[Lägg till function_call + function_call_output]
    G --> C
    E -->|message| H[Extrahera text]
    H --> I[Visa för användare]
    I --> A
```

# Delivery Steps

### ✓ Step 1: Implementera nya data modeller för Responses API
Ersätt befintliga record-er med nya som matchar Responses API:ts format.
- Ta bort `Request`, `Message`, `OpenRouterResponse`, `Choice`, `MessageResponse`, `ToolCall`, `ToolFunction`
- Lägg till `Request` med `model`, `instructions`, `input`, `tools`
- Lägg till `InputItem` med stöd för `message`, `function_call`, och `function_call_output` typer
- Lägg till `ContentPart` för input content
- Lägg till `Response`, `OutputItem`, `OutputContentPart`, och `Usage` för response parsing
- Behåll `Tool` och `ToolFunctionSpec` oförändrade (samma format i båda API:n)
- Behåll `DateTimeResult` oförändrad

### ✓ Step 2: Uppdatera huvudslingan och API-anropet
Anpassa main-metoden för att använda det nya API-formatet.
- Ändra endpoint från `/api/v1/chat/completions` till `/api/v1/responses`
- Extrahera system message till `instructions` fält istället för att skicka som message
- Bygga `input` array med `MessageItem` istället för `messages`
- Parsa `output` array från response istället för `choices`
- Leta efter `function_call` items i output för verktygsanrop
- Vid verktygsanrop: lägga till både `function_call` och `function_call_output` items i input
- Vid `message` item: extrahera text från `content[].output_text.text`
- Ta bort `finish_reason` logik – ersätt med att kolla vilka item-typer som finns i output
- Uppdatera verktygsimplementeringarna för att använda `call_id` från `function_call` items
- Behåll samma bekräftelseslinga och användarinteraktion