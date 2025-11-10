Microservices bundle (minimal).
Projects: config-server, eureka-server, user-service, api-gateway.
Run order:
1) config-server (port 8888)
2) eureka-server (port 8761)
3) user-service (port 8080)
4) api-gateway (port 8081)
   Notes:
- Config server serves local config-repo included under config-server.
- For full user-service DB functionality, provide Postgres or change datasource to H2.
