from xmlrpc.server import SimpleXMLRPCServer

# Define the remote methods
def add(a, b):
    return a + b

def subtract(a, b):
    return a - b

def multiply(a, b):
    return a * b

def divide(a, b):
    if b == 0:
        return "Division by zero is not allowed"
    return a / b

def square(a):
    return a * a

# Set up the server
server = SimpleXMLRPCServer(("localhost", 9000))
print("Server is running on port 9000...")

# Register functions
server.register_function(add)
server.register_function(subtract)
server.register_function(multiply)
server.register_function(divide)
server.register_function(square)

# Start the server
server.serve_forever()
