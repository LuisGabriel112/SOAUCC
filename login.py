from spyne import Application, rpc, ServiceBase, Unicode, Boolean
from spyne.protocol.soap import Soap11
from spyne.server.wsgi import WsgiApplication

class loginService(ServiceBase):
    @rpc(Unicode, Unicode, _returns=Unicode)
    def login(ctx, username, password):
        if username == "usuario" and password == "clave123":
            return f"Bienvenido, {username}"
        else:
            return f"Credenciales incorrectas"


application = Application(
    [loginService],
    tns='mi.namespace',
    in_protocol=Soap11(validator='lxml'),
    out_protocol=Soap11()
)

if __name__ == '__main__':
    from wsgiref.simple_server import make_server
    server = make_server('0.0.0.0', 8001, WsgiApplication(application))
    print("Servicio SOAP en http://0.0.0.0:8001")
    server.serve_forever()
