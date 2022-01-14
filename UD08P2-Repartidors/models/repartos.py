# -*- coding: utf-8 -*-

from odoo import models, fields, api

#Definimos el modelo de datos
class lista_repartos(models.Model):
    #Nombre y descripcion del modelo de datos
    _name = 'lista_repartos'
    _description = 'Modelo de la lista de repartos'
    #Como no tenemos un atributo "name" en nuestro modelo, indicamos que cuando
    #se necesite un nombre, se usara el atributo tarea
    _rec_name="repartidor"

    #Elementos de cada fila del modelo de datos
    #Los tipos de datos a usar en el ORM son 
    # https://www.odoo.com/documentation/14.0/developer/reference/addons/orm.html#fields
   
    id = fields.Integer()
    fecha_inicio = fields.Date()
    fecha_retorno = fields.Date()
    fecha_recepcion = fields.Date()
    repartidor = fields.Many2one()
    vehiculo = fields.Many2one()
    kilometros = fields.Integer()
    peso = fields.Integer()
    volumen = fields.Integer()
    observaciones = fields.Char()
    estado_entrega = fields.Selection([ ('nada', 'No ha salido'),
                                ('camino', 'En camino'),
                                ('entregado', 'Entregado'),])
    emisor = fields.Many2one()
    receptor = fields.Many2one()
    
