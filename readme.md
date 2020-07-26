``` THis branch contain integration of opa with spring only```

curl --location --request PUT 'localhost:8181/v1/policies/opaweb/authz' \
  --header 'Content-Type: text/plain' \
  --data-binary @opaweb-policy.rego 

curl -kv http://localhost:8080/hello --header 'Authorization: Basic am9objEyMzpwYXNzd29yZA=='

curl -kv http://localhost:8080/bye --header 'Authorization: Basic YWRtaW4xMjM6YWRtaW4='

postman link -> https://www.getpostman.com/collections/8565e0f5d433cb9b9526

Usage : 

-> This is just a simple spring app with opa authorization for api access, with two roles defined in MyUserDetailService class

-> For testing is using postman .. download the collection from above link 

-> switch roles in MyUserDetailService for hello api access, to user role

-> switch roles in MyUserDetailService for hello api access, to admin role


