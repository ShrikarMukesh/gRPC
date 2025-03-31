## Overview
gRPC (Google Remote Procedure Call) is a high-performance, open-source RPC framework developed by Google. 
It enables efficient communication between services using **Protocol Buffers (Protobufs)** over **HTTP/2**.

---

## 1. Key Features of gRPC
- 🚀 **Uses HTTP/2** → Supports multiplexing, reducing latency  
- ⚡ **Protocol Buffers (Protobuf)** → Efficient binary serialization  
- 🔄 **Streaming Support** → Client, server, and bidirectional streaming  
- 🌍 **Multi-language support** → Java, Python, Go, C++, etc.  
- 🔒 **Authentication & Security** → Supports TLS, OAuth2, JWT, and mTLS  
- 🤖 **Automatic Code Generation** → Generates server and client stubs  

---

## 2. How gRPC Works
1. **Define Service & Messages** in a `.proto` file  
2. **Generate Code** using `protoc` compiler  
3. **Implement the Server** using the generated base class  
4. **Create the Client** to call remote methods  

---

## 3. gRPC vs REST
| Feature  | gRPC | REST |
|----------|------|------|
| **Protocol** | HTTP/2 | HTTP 1.1 / 2 |
| **Data Format** | Binary (Protobuf) | JSON (Text-based) |
| **Performance** | Faster | Slower (JSON parsing) |
| **Streaming** | Supported | Limited |
| **Security** | TLS, OAuth2, mTLS | HTTPS, OAuth2 |
| **Tooling** | Requires gRPC libraries | Works natively in browsers |
| **Best Use Case** | Microservices, real-time apps | Public APIs, web services |

---

## 4. gRPC Communication Types
- **Unary RPC** → One request, one response  
- **Server Streaming** → Client sends a request, server streams responses  
- **Client Streaming** → Client streams multiple requests, server responds once  
- **Bidirectional Streaming** → Both client and server send multiple messages  

---

## 5. Sample gRPC Implementation in Java
### **Define gRPC Service (`user.proto`)**
```proto
syntax = "proto3";
package user;
service UserService {
    rpc GetUser(UserRequest) returns (UserResponse);
}
message UserRequest {
    int32 id = 1;
}
message UserResponse {
    int32 id = 1;
    string name = 2;
}


# REST vs gRPC

## Overview
This document compares REST and gRPC, highlighting their differences, advantages, and use cases.

## Key Differences
| Feature  | REST (Representational State Transfer) | gRPC (Google Remote Procedure Call) |
|----------|--------------------------------------|----------------------------------|
| **Protocol** | Uses HTTP 1.1/2 with standard methods (GET, POST, PUT, DELETE) | Uses HTTP/2 with binary data over Protocol Buffers |
| **Data Format** | JSON (text-based, human-readable) | Protocol Buffers (binary, compact, efficient) |
| **Performance** | Slower due to JSON serialization and HTTP 1.1 overhead | Faster due to HTTP/2 multiplexing and binary serialization |
| **Communication** | Typically synchronous request-response | Supports bidirectional streaming, client streaming, and server streaming |
| **Error Handling** | Uses HTTP status codes (e.g., 200, 404, 500) | Uses rich error messages and status codes from Protocol Buffers |
| **Security** | Uses HTTPS, OAuth, JWT for authentication | Uses TLS, mTLS, and supports authentication via Google IAM, JWT, etc. |
| **Interoperability** | Works well across different clients (browsers, mobile, etc.) | Requires gRPC support, not natively supported in browsers |
| **Tooling Support** | Well-supported in browsers and various languages | Requires gRPC libraries and support in client languages |
| **Best Use Cases** | Web APIs, public APIs, microservices with browser clients | Microservices, real-time applications, internal services |

## REST Overview
REST is an architectural style that relies on standard HTTP methods (GET, POST, PUT, DELETE) to enable communication between services.

### Example REST API in Java (Spring Boot)
```java
@RestController
@RequestMapping("/api/users")
public class UserController {
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
}
```

## gRPC Overview
gRPC is a high-performance RPC framework that enables communication between services using Protocol Buffers and HTTP/2.

### Example gRPC Service Definition (`user.proto`)
```proto
syntax = "proto3";

package user;

service UserService {
    rpc GetUser (UserRequest) returns (UserResponse);
}

message UserRequest {
    int32 id = 1;
}

message UserResponse {
    int32 id = 1;
    string name = 2;
    string email = 3;
}
```

### Example gRPC Server Implementation (Java)
```java
public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {
    @Override
    public void getUser(UserRequest request, StreamObserver<UserResponse> responseObserver) {
        UserResponse response = UserResponse.newBuilder()
                .setId(request.getId())
                .setName("John Doe")
                .setEmail("john.doe@example.com")
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
```

## When to Use REST vs gRPC
### **Use REST When:**
✔ You need **browser support** (e.g., public APIs).  
✔ You want a **human-readable** format like JSON.  
✔ You are building a simple **CRUD-based API**.  
✔ Your clients are not tightly coupled with your backend.

### **Use gRPC When:**
✔ You need **high performance** (e.g., low-latency services).  
✔ You need **real-time streaming** (e.g., chat, live updates).  
✔ You are developing **internal microservices**.  
✔ You are working with **multiple languages** and want better **serialization** efficiency.  

## Conclusion
- **REST** is more widely supported and best suited for web-based APIs and external services.
- **gRPC** is **faster**, supports **streaming**, and is **ideal for microservices** and real-time systems.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contributing
Feel free to submit pull requests or open issues!

## Contact
For questions, reach out to **your.email@example.com**.

