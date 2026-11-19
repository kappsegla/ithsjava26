package com.example.java26.agent;

import tools.jackson.databind.json.JsonMapper;
import java.io.*;
import java.util.*;

public class Weather {
  public static void main(String[] a) throws Exception {
    var m = JsonMapper.builder().build();
    var in = new BufferedReader(new InputStreamReader(System.in));
    for (String l; (l = in.readLine()) != null; ) {
      var req = m.readTree(l);
      if (!req.has("id")) continue;                       // notifikation: svara inte
      Object res = Map.of();                              // ping och okända metoder
      switch (req.path("method").asString()) {
        case "initialize" -> res = Map.of(
            "protocolVersion", req.at("/params/protocolVersion").asString(),
            "capabilities", Map.of("tools", Map.of()),
            "serverInfo", Map.of("name", "weather", "version", "0"));
        case "tools/list" -> res = Map.of("tools", List.of(Map.of(
            "name", "get_weather",
            "description", "Returns the current weather for a city (sun, rain or snow)",
            "inputSchema", Map.of("type", "object",
                "properties", Map.of("city", Map.of("type", "string")),
                "required", List.of("city")))));
        case "tools/call" -> {
          String city = req.at("/params/arguments/city").asString("okänd stad");
          String w = List.of("sol", "regn", "snö").get(new Random().nextInt(3));
          res = Map.of("content", List.of(Map.of("type", "text", "text", city + ": " + w)));
        }
        default -> {}
      }
      System.out.println(m.writeValueAsString(
          Map.of("jsonrpc", "2.0", "id", req.get("id"), "result", res)));
    }
  }
}
