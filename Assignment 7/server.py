from spyne import Application, rpc, ServiceBase, Iterable, Integer, String, Unicode, AnyXml, AnyDict, Decimal
from spyne.protocol.soap import Soap11
from spyne.server.wsgi import WsgiApplication
from pymongo import MongoClient
import lxml

#MongoDB database creation and connection
try:
    connect = MongoClient()
    print("Database is connected\n")
except:
    print("ERROR! Database still not connected\n")

db = connect.CustomerDB
collection = db.CustomerCollection

############################################# SOAP ##################################################

class CustomerService(ServiceBase):
    #Inserting data into the database
    @rpc(Integer, Unicode, Unicode, Unicode, Integer, Integer, _returns = Unicode)
    def insert_record(ctx, CustomerID, CustomerFN, CustomerLN, CustomerEmail, CustomerMobile, CustomerBalance):
        record = {
            "customerID": CustomerID,
            "customerFirstName": CustomerFN,
            "customerLastName": CustomerLN,
            "customerEmail": CustomerEmail,
            "customerMobile": CustomerMobile,
            "customerBalance": CustomerBalance
        }

        collection.insert_one(record)

    #Reading data from the database
    @rpc(_returns = Iterable(AnyDict))
    def find_records(ctx):
        cursor = collection.find()
        array = []
        for record in cursor:
            array.append(record)
        return array

    #Deleting data from the database
    @rpc(_returns = Iterable(Unicode))
    def delete_recs(ctx):
        collection.delete_many({})
        print('Data cleared successful\n')


app = Application([CustomerService], 'spyne.examples.hello.soap',
                  in_protocol = Soap11(validator='lxml'), out_protocol=Soap11())

wsgi_app = WsgiApplication(app)

if __name__ == '__main__':
    import logging
    from wsgiref.simple_server import make_server

    logging.info('Server is now listening\n')
    logging.info('wsdl is now listening\n')
    server = make_server('0.0.0.0', 7000, wsgi_app)
    server.serve_forever()
