curl --location --request PUT '192.168.99.100:8181/v1/policies/opaweb/authz' \
--header 'Content-Type: text/plain' \
--data-binary @opaweb-policy.rego