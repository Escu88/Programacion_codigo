from kivy.lang import Builder
from kivymd.app import MDApp
import hashlib
import csv
#USUARIOS Y CONTRASEÑAS
#user1:pass1
#user2:pass2

user = []
passwords = []
#sacar la informacion del csv
with open('users.csv',newline='') as csvFile:
    usuarios = csv.reader(csvFile)
    for row in usuarios:
        passwords.append(row[1])
        user.append(row[0])


class MainApp(MDApp): #Clase principal
	def build(self): #Cambiar el tema
		self.theme_cls.theme_style = "Dark"
		self.theme_cls.primary_palette = "Purple"
		return Builder.load_file('login.kv')
	def logger(self): #Comprobar si el usuario y contraseña son correctos
		contra = self.root.ids.password.text #Coger la contraseña que ha introducido el usuario
		userpass = hashlib.sha1(str.encode(contra)).hexdigest() #Se pasa la contraseña a Sha1
		strpass = userpass.upper() #Se paasa el Sha1 a mayúsculas para que no haya diferencia de formato
		usu = self.root.ids.user.text #Coger el nombre de usaurio introducido
		
		#Para comprobar si los datos son correctos
		if (usu in user): 
			if (strpass in passwords and user.index(usu) == passwords.index(strpass)):
				self.root.ids.welcome_label.text = f'OK {self.root.ids.user.text}!'
			else:
				self.root.ids.welcome_label.text = f'ERROR {self.root.ids.user.text}!'
		else:
				self.root.ids.welcome_label.text = f'ERROR {self.root.ids.user.text}!'


	def clear(self):
		self.root.ids.welcome_label.text = "BIENVENIDO"		
		self.root.ids.user.text = ""		
		self.root.ids.password.text = ""		
	
MainApp().run()