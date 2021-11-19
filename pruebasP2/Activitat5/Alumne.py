from Professor import Professor

class Alumne: #declarar la clase
    nom = ""
    curs = ""
    professor = ""

    #constructor de la clase
    def __init__(self, nombre, curso, profe): 
        self.nom = nombre
        self.curs = curso
        self.professor = profe

    #getters y setters
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
    
    #función str, en la cual se decide el formato de como se mostrará la clase al printearla
    def __str__(self):
        return "Nombre: "+str(self.nom)+" | Curso: "+str(self.curs)+" | Professor: "+str(self.professor)