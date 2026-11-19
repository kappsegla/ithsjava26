package com.example.java26.agent;

import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;
import java.util.Map;

class McpClient implements AutoCloseable {
  private final ObjectMapper m = JsonMapper.builder().build();
  private final Process p;
  private final java.io.BufferedReader in;
  private final java.io.BufferedWriter out;
  private int id = 0;

  McpClient(List<String> cmd, Map<String, String> env) throws Exception {
    var pb = new ProcessBuilder(cmd);
    pb.environment().putAll(env);
    pb.redirectError(ProcessBuilder.Redirect.INHERIT);   // serverns loggar till din konsol
    p = pb.start();
    in = p.inputReader();
    out = p.outputWriter();
    call("initialize", Map.of("protocolVersion", "2024-11-05", "capabilities", Map.of(),
        "clientInfo", Map.of("name", "my-agent", "version", "0")));
    send(Map.of("jsonrpc", "2.0", "method", "notifications/initialized"));  // inget id = inget svar
  }

  tools.jackson.databind.JsonNode listTools() throws Exception {
    return call("tools/list", Map.of()).get("tools");
  }

  String callTool(String name, Map<String, Object> args) throws Exception {
    var r = call("tools/call", Map.of("name", name, "arguments", args));
    var sb = new StringBuilder();
    for (var c : r.path("content")) sb.append(c.path("text").asString(""));
    return sb.toString();
  }

  private tools.jackson.databind.JsonNode call(String method, Object params) throws Exception {
    int mine = ++id;
    send(Map.of("jsonrpc", "2.0", "id", mine, "method", method, "params", params));
    for (String l; (l = in.readLine()) != null; ) {
      var n = m.readTree(l);
      if (n.has("id") && !n.has("method") && n.get("id").asInt() == mine) return n.get("result");
    }
    throw new RuntimeException("MCP-servern stängdes");
  }

  private void send(Object msg) throws Exception {
    out.write(m.writeValueAsString(msg) + "\n");
    out.flush();                                          // utan flush händer ingenting
  }

  public void close() { p.destroy(); }
}
