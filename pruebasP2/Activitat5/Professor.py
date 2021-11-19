class Professor: #declaración de la clase
    nom = ""
    tipus = ""

    #constructor
    def __init__(self, nombre, tipo):
        if (tipo == "letras" or tipo == "ciencias" or tipo == "mixto"):
            self.nom = nombre
            self.tipus = tipo
        else:
            print("Tipo de profesor no válido")
    
    #getters y setters
    def get_Nom(self):
        return self.nom
    
    def get_Tipus(self):
        return self.tipus
    
    def get_Nom(self, nombre):
        self.nom = nombre

    def get_Tipus(self, tipo):
        self.tipus = tipo
    
    #función str, en la cual se decide el formato de como se mostrará la clase al printearla
    def __str__(self):
        return "Nombre: "+str(self.nom)+" | Tipo: "+str(self.tipus)
        