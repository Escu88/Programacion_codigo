import Activitat6
from models import Alumne

def run():
    #Esta linea printea los alumos que hay ya creados, para crearlos, descomentar las siguientes
    alumnos = Activitat6.session.query(Alumne).all()
    print(alumnos)
    #Crear los alumnos
"""alum1 = Alumne('alum1', '1','profesor1')
    Activitat6.session.add(alum1)
    alum2 = Alumne('alum2', '2','profesor2')
    Activitat6.session.add(alum2)
    Activitat6.session.commit()"""


if __name__ == '__main__':
    Activitat6.Base.metadata.create_all(Activitat6.engine)
    run()