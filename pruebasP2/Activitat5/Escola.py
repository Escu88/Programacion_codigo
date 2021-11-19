class Escola: #declarar la clase
    nom = ""
    localitat = ""
    responsable = ""
    alumnos = []
    profesores = []

    #constructor de la clse
    def __init__(self, nombre, localidad, respon):
        self.nom = nombre
        self.localitat = localidad
        self.responsable = respon
    
    #getters y setters
    def get_Nom(self):
        return self.nom

    def get_Localitat(self):
        return self.localitat
    
    def get_Responsable(self):
        return self.responsable
    
    def set_Nom(self, nombre):
        self.nom = nombre
    
    def set_Localitat(self, localidad):
        self.localitat = localidad
    
    def set_Responsable(self, respon):
        self.responsable = respon

    def add_Alum(self, alum):
        self.alumnos.append(alum)

    def add_Prof(self, prof):
        self.profesores.append(prof)

    def del_Alum(self, alum):
        self.alumnos.remove(alum)

    def del_Prof(self, prof):
        self.profesores.remove(prof)

    #función str, en la cual se decide el formato de como se mostrará la clase al printearla
    def __str__(self):
        return "Escuela: "+str(self.nom)+" | Localidad: "+str(self.localitat)+" | Responsable: "+str(self.responsable)