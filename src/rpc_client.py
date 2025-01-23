import xmlrpc.client

# Connect to the server
server = xmlrpc.client.ServerProxy("http://localhost:9000/")

while True:
    print("\nAvailable operations: add, subtract, multiply, divide, square, exit")
    operation = input("Enter the operation name: ").strip().lower()

    if operation == "exit":
        print("Exiting the client.")
        break

    try:
        if operation in ["add", "subtract", "multiply", "divide"]:
            a = float(input("Enter the first number: "))
            b = float(input("Enter the second number: "))
            if operation == "add":
                result = server.add(a, b)
            elif operation == "subtract":
                result = server.subtract(a, b)
            elif operation == "multiply":
                result = server.multiply(a, b)
            elif operation == "divide":
                result = server.divide(a, b)
        elif operation == "square":
            a = float(input("Enter the number: "))
            result = server.square(a)
        else:
            print("Invalid operation. Please try again.")
            continue

        print(f"Result of {operation}: {result}")
    except Exception as e:
        print(f"An error occurred: {e}")
