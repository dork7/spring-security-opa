package opaweb.authz

default allow = false

allow {
  input.method == "GET"
  input.path = ["hello"]
  #input.path = ["opa_app" , "hello"]
  #input.path = "/opa_app/bye"
  # is_user
  claims.name == "salman"
}

allow {
  input.method == "GET"
  input.path = ["bye"]
  #input.path = ["opa_app" ,"bye"]
 # is_admin
  claims.name == "hamza"
}


# user is allowed if he has a user role
is_user {

	# for some `i`...
  some i

  input.roles[i].authority == "ROLE_USER"
}

# user is allowed if he has a admin role
is_admin {

	# for some `i`...
	some i

    input.roles[i].authority == "ROLE_ADMIN"
}

claims := payload {

	# io.jwt.verify_hs256(bearer_token, "secret")

	[_, payload, _] := io.jwt.decode(bearer_token)
}

bearer_token := t {
	v := input.headers.jwt
	startswith(v, "Bearer ")
	t := substring(v, count("Bearer "), -1)
}