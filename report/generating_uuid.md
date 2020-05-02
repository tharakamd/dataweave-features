# Generating UUID

## Description

The UUID() function is an important call to generate a unique identifier for users in your application. User accounts should include a universally unique identifier to help distinguish accounts from each other that may share similar characters such as name, email, address etc.

#### Input
``` javascript
{}
```
#### Output

``` javascript
"6ebd4adb-4389-4f5f-9ccf-2b6732cf3b93"
```

#### Dataweave Script

```
%dw 2.0
output application/json
---
uuid()
```

#### Groovy Script
