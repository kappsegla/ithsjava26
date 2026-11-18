---
name: database-tools
description: Use when the user wants to query, browse, or manage SQL databases — listing schemas, inspecting tables, previewing data, or running SQL queries.
---

Use these tools to browse database schemas, introspect metadata, preview table data, and execute SQL queries against configured database connections. Prefer them over the terminal (`psql`, `mysql`, generic shell) for querying and exploring databases.

## Prerequisites

- A database connection must already exist. Use the `database-connection-management` skill to create or verify connections first.
- Always resolve the `connectionId` via `list_database_connections` — never invent or guess it.

## Core Concepts

- **`connectionId`** — the unique id of a configured data source; required for all database tools. Get it with `list_database_connections`.
- **`databaseName`** — the database within the connection. For DBMS without a database layer (e.g., PostgreSQL), this may be an empty string `''`.
- **`schemaName`** — the schema (namespace) within the database. Required for most tools.
- **Object kinds** — database objects have a `kind` field: `table`, `view`, `routine`, `scheduled-event`, etc. Use `list_schema_object_kinds` to discover available kinds.

## Tool Capabilities

Use the universal `execute_tool` to call any of the database tools.

### Command syntax

The `command` string has exactly one accepted shape:

```
toolName --paramName1 value1 --paramName2 value2
```

- Every parameter is a `--`-prefixed flag, separated from its value by a **space** — never `=`.
- **Do not** use `name=value` or `--name=value`. **Do not** use positional arguments. All are rejected.
- Quote values that contain spaces, e.g. `--schemaName 'public schema'`.

Good vs. bad:
- Good: `execute_sql_query --connectionId <id> --databaseName '' --schemaName sakila --queryText "SELECT 1"`
- Bad: `execute_sql_query --queryText="SELECT 1"` (uses `=`)
- Bad: `execute_sql_query <id> "SELECT 1"` (positional)

### Discover connections

- `execute_tool(command="list_database_connections")` — lists every configured data source with `id`, `name`, `dbms`, `driverName`, `isDDL`, `readOnly`, `jdbcUrl`. Run first to resolve a `connectionId` before any other operation.

### List schemas

- `execute_tool(command="list_database_schemas --connectionId <id>")` — retrieves all schemas in the connection. Returns `databaseName`, `schemaName`, and `isIntrospected` for each schema.

Parameters:
- `connectionId` — required; id of the data source.

### List object kinds

- `execute_tool(command="list_schema_object_kinds --connectionId <id>")` — retrieves the supported schema object kinds for the connection (e.g., `table`, `view`, `routine`, `scheduled-event`). Use this to know which `kind` values are valid for filtering in `list_schema_objects`.

Parameters:
- `connectionId` — required; id of the data source.

### List schema objects

- `execute_tool(command="list_schema_objects --connectionId <id> --schemaName <schema> --databaseName <db>")` — lists all database objects (tables, views, routines, etc.) within a schema. Returns `name` and `kind` for each object.

Parameters:
- `connectionId` — required; id of the data source.
- `schemaName` — required; name of the schema.
- `databaseName` — required; name of the database (pass `''` empty string if the DBMS has no database layer).
- `kind` — optional; filter by object kind (e.g., `table`, `view`, `routine`). Omit to list all objects.

### Preview table data

- `execute_tool(command="preview_table_data --connectionId <id> --schemaName <schema> --databaseName <db> --tableName <table>")` — returns a preview of table contents in CSV format. Ideal for quick data exploration without writing SQL.

Parameters:
- `connectionId` — required; id of the data source.
- `schemaName` — required; name of the schema.
- `databaseName` — required; name of the database (pass `''` empty string if not applicable).
- `tableName` — required; name of the table (or view/materialized view).

The tool returns a `resultSetId` and `text` containing CSV-formatted data with headers on the first line.

### Execute SQL query

- `execute_tool(command="execute_sql_query --connectionId <id> --schemaName <schema> --databaseName <db> --queryText <sql>")` — executes an arbitrary SQL query and returns the result set as formatted text.

Parameters:
- `connectionId` — required; id of the data source.
- `schemaName` — required; name of the schema.
- `databaseName` — required; name of the database (pass `''` empty string if not applicable).
- `queryText` — required; the SQL query to execute.

The tool returns a `resultSetId` and `text` containing the query results.

## Workflow Patterns

### Explore a database from scratch

1. `list_database_connections` — find the connection id.
2. `list_database_schemas --connectionId <id>` — discover available schemas.
3. `list_schema_object_kinds --connectionId <id>` — see what object types exist.
4. `list_schema_objects --connectionId <id> --schemaName <schema> --databaseName <db>` — list all objects.
5. `preview_table_data --connectionId <id> --schemaName <schema> --databaseName <db> --tableName <table>` — peek at table contents.
6. `execute_sql_query --connectionId <id> --schemaName <schema> --databaseName <db> --queryText <sql>` — run custom queries.

### Find a specific table

1. `list_database_connections` — get the connection id.
2. `list_database_schemas --connectionId <id>` — pick the right schema.
3. `list_schema_objects --connectionId <id> --schemaName <schema> --databaseName <db> --kind table` — list only tables.
4. `preview_table_data --connectionId <id> --schemaName <schema> --databaseName <db> --tableName <table>` — inspect the table data.

### Run a custom query

1. `list_database_connections` — get the connection id.
2. `list_database_schemas --connectionId <id>` — identify the schema.
3. `execute_sql_query --connectionId <id> --schemaName <schema> --databaseName <db> --queryText "SELECT ..."` — execute the query.

## Minimal Examples

```json
{"tool":"execute_tool","arguments":{"command":"list_database_connections"}}
```

```json
{"tool":"execute_tool","arguments":{"command":"list_database_schemas --connectionId e8c44cc8-3f3e-4137-af43-b8334d1659f8"}}
```

```json
{"tool":"execute_tool","arguments":{"command":"list_schema_object_kinds --connectionId e8c44cc8-3f3e-4137-af43-b8334d1659f8"}}
```

```json
{"tool":"execute_tool","arguments":{"command":"list_schema_objects --connectionId e8c44cc8-3f3e-4137-af43-b8334d1659f8 --schemaName sakila --databaseName ''"}}
```

```json
{"tool":"execute_tool","arguments":{"command":"list_schema_objects --connectionId e8c44cc8-3f3e-4137-af43-b8334d1659f8 --schemaName sakila --databaseName '' --kind table"}}
```

```json
{"tool":"execute_tool","arguments":{"command":"preview_table_data --connectionId e8c44cc8-3f3e-4137-af43-b8334d1659f8 --schemaName sakila --databaseName '' --tableName actor"}}
```

```json
{"tool":"execute_tool","arguments":{"command":"execute_sql_query --connectionId e8c44cc8-3f3e-4137-af43-b8334d1659f8 --schemaName sakila --databaseName '' --queryText 'SELECT COUNT(*) FROM actor'"}}
```

## Short Rules

- Pass arguments as `--paramName value` (space-separated, `--` prefix). Never `name=value` or `--name=value`, never positional.
- Never hard-code or invent a `connectionId`; always resolve it through `list_database_connections`.
- Always pass `databaseName` even when it is empty — use `''` (empty string) for DBMS without a database layer.
- Use `preview_table_data` for quick table inspection instead of writing `SELECT *` queries.
- Use `list_schema_objects` with `--kind table` to filter only tables, or `--kind view` for views, etc.
- Use `list_schema_object_kinds` to discover which `kind` values are supported for a connection.
- For custom queries, use `execute_sql_query` with `--queryText` (not `--sql`).
- Present CSV results from `preview_table_data` as formatted tables for readability.
- After a connection exists, use this skill to browse schemas, introspect metadata, preview tables, and run SQL.
