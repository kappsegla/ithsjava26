package com.example.java26.agent;

import tools.jackson.databind.json.JsonMapper;
import java.io.*;
import java.sql.*;
import java.util.*;

public class Sql {
  public static void main(String[] a) throws Exception {
    var m = JsonMapper.builder().build();
    var in = new BufferedReader(new InputStreamReader(System.in));
    for (String l; (l = in.readLine()) != null; ) {
      var req = m.readTree(l);
      if (!req.has("id")) continue;
      Object res = Map.of();
      switch (req.path("method").asString()) {
        case "initialize" -> res = Map.of(
            "protocolVersion", req.at("/params/protocolVersion").asString(),
            "capabilities", Map.of("tools", Map.of()),
            "serverInfo", Map.of("name", "sql", "version", "0"));
        case "tools/list" -> res = Map.of("tools", List.of(Map.of(
            "name", "query",
            "description", "Run a read-only SQL query (SELECT/SHOW/DESCRIBE). Max 200 rows.",
            "inputSchema", Map.of("type", "object",
                "properties", Map.of("sql", Map.of("type", "string")),
                "required", List.of("sql")))));
        case "tools/call" -> {
          String text; boolean err = false;
          try (var db = DriverManager.getConnection(System.getenv("MYSQL_URL"),
                   System.getenv("MYSQL_USER"), System.getenv("MYSQL_PASSWORD"));
               var st = db.createStatement()) {
            db.setReadOnly(true);                         // 2:a försvarslinjen
            st.setMaxRows(200);
            st.setQueryTimeout(15);
            var rs = st.executeQuery(req.at("/params/arguments/sql").asString());
            var md = rs.getMetaData();
            var rows = new ArrayList<Map<String, String>>();
            while (rs.next()) {
              var r = new LinkedHashMap<String, String>();
              for (int i = 1; i <= md.getColumnCount(); i++) r.put(md.getColumnLabel(i), rs.getString(i));
              rows.add(r);
            }
            text = m.writeValueAsString(rows);
          } catch (Exception e) { text = e.toString(); err = true; }   // felet går tillbaka till modellen
          res = Map.of("content", List.of(Map.of("type", "text", "text", text)), "isError", err);
        }
        default -> {}
      }
      System.out.println(m.writeValueAsString(
          Map.of("jsonrpc", "2.0", "id", req.get("id"), "result", res)));
    }
  }
}
