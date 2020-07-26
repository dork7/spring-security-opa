package opaweb.authz

test_hello_allowed_for_user {
    allow with input as {"path": ["hello"], "method": "GET", "roles": [{"authority": "ROLE_USER"}]}
}

test_hello_denied_for_others {
    not allow with input as {"path": ["hello"], "method": "GET", "roles": [{"authority": "ROLE_ADMIN"}]}
    not allow with input as {"path": ["hello"], "method": "GET", "roles": [{"authority": "ROLE_ANONYMOUS"}]}
}

test_bye_allowed_for_admin {
    allow with input as {"path": ["bye"], "method": "GET", "roles": [{"authority": "ROLE_ADMIN"}]}
}

test_bye_denied_for_others {
    not allow with input as {"path": ["bye"], "method": "GET", "roles": [{"authority": "ROLE_USER"}]}
    not allow with input as {"path": ["bye"], "method": "GET", "roles": [{"authority": "ROLE_ANONYMOUS"}]}
}