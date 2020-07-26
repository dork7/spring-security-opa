curl --location --request PUT 'localhost:8181/v1/policies/opaweb/authz' \
  --header 'Content-Type: text/plain' \
  --data-binary @opaweb-policy.rego 

curl -kv http://localhost:8080/hello --header 'Authorization: Basic am9objEyMzpwYXNzd29yZA=='

curl -kv http://localhost:8080/bye --header 'Authorization: Basic YWRtaW4xMjM6YWRtaW4='