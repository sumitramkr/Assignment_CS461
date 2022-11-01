from pickletools import float8
from suds.client import Client

hello_client = Client('http://localhost:7000/?wsdl')

print("\n1.Enter a new data\n")
print("2.Display entire data\n")
print("3.Delete enitre data\n")
print("Enter choice number: ", end = ' ')

choice = int(input())
print("Your choice selected is: ", choice, "\n")

customerID = 0
if choice == 1:
    customerID += 1
    customerFN = input("Enter first name: ")
    print()
    customerLN = input("Enter last name: ")
    print()
    customerEmail = input("Enter the email: ")
    print()
    customerMobile = int(input("Enter phone number: "))
    print()
    customerBalance = int(input("Enter the balance: "))
    print("\nStatus: ")

    msg = hello_client.service.insert_record(1, customerFN, customerLN, customerEmail, customerMobile, customerBalance)
    print(msg)

elif choice == 2:
    data = hello_client.service.find_records()
    for record in data:
        print(record)
else:
    hello_client.service.delete_recs()
