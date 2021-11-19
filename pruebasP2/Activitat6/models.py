#from Professor import Professor

import Activitat6

from sqlalchemy import Column, String

class Alumne(Activitat6.Base):
    __tablename__ = 'alumne'
    nom = Column(String, primary_key=True)
    curs = Column(String, nullable=False)
    professor = Column(String)

    def __repr__(self):
        return f'Alumno -> Nombre: {self.nom}, Curso: {self.curs}, Profesor: {self.professor}\n'

    def __init__(self, nombre, curso, profe = "professor"):
        self.nom = nombre
        self.curs = curso
        self.professor = profe

    def get_Nom(self):
        return self.nom

    def get_Curs(self):
        return self.curs

    def get_Professor(self):
        return self.professor   

    def set_Nom(self, nombre):
        self.nom = nombre

    def set_Nom(self, curso):
        self.curs = curso
    
    def set_Nom(self, profesor):
        self.professor = profesor
    
    def __str__(self):
        return "Nombre: "+str(self.nom)+" | Curso: "+str(self.curs)+" | Professor: "+str(self.professor)