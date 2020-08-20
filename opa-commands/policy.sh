curl --location --request PUT 'localhost:8181/v1/policies/opaweb/authz' \
--header 'Content-Type: text/plain' \
--data-binary @opaweb-policy.rego