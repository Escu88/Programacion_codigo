# -*- coding: utf-8 -*-

from odoo import models, fields, api

#Definimos el modelo de datos
class lista_vehiculos(models.Model):
    #Nombre y descripcion del modelo de datos
    _name = 'lista_vehiculos'
    _description = 'Modelo de la lista de empleados'
    #Como no tenemos un atributo "name" en nuestro modelo, indicamos que cuando
    #se necesite un nombre, se usara el atributo tarea
    _rec_name="tipo"

    #Elementos de cada fila del modelo de datos
    #Los tipos de datos a usar en el ORM son 
    # https://www.odoo.com/documentation/14.0/developer/reference/addons/orm.html#fields
   
    tipo = fields.Char()
    matricula = fields.Char()
    foto = fields.Image(max_width=64, max_height=64)
    descripcion = fields.Char() 
