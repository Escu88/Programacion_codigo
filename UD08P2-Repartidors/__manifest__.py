# -*- coding: utf-8 -*-
{
    'name': "Empresa de repartidors",

    'summary': """
    Sencilla Lista de repartidors""",

    'description': """
    Sencilla lista repartos;
    """,

    'author': "Adrián Escudero",
    'website': "https://apuntesfpinformatica.es",
    #Indicamos que es una aplicación
    'application': True,

    # En la siguiente URL se indica que categorias pueden usarse
    # https://github.com/odoo/odoo/blob/14.0/odoo/addons/base/data/ir_module_category_data.xml
    # Vamos a utilizar la categoria Productivity
    'category': 'Productivity',
    'version': '0.1',

    # Indicamos lista de modulos necesarios para que este funcione correctamente
    # En este ejemplo solo depende del modulo "base"
    'depends': ['base'],

    # Esto siempre se carga
    'data': [
        #Este primero indica la politica de acceso del modulo
        'security/ir.model.access.csv',
        #Cargamos las vistas y las plantillas
        'views/empleados.xml',
        'views/vehiculos.xml',
        'views/clientes.xml',
        'views/repartos.xml',

    ]
}