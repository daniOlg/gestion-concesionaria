### Instrucciones 1
- Los vehículos se clasifican en: autos, camionetas y motocicletas.
- Todos los vehículos tienen código único, su marca, tipo (auto, camioneta, etc.), modelo (nombre del modelo), año, kilometraje y patente. 
- La gestorVehiculos lleva un registro de las ventas realizadas, cada registro considera la siguiente información: monto de venta, fecha de la transacción, vehículo vendido (código), nombre y apellido del comprador, así como su rut.
- Utilice clases, sobrecarga y por último, incluya el control de excepciones try-catch de acuerdo a lo aprendido durante la unidad.

### Instrucciones 2
- Estimado(a) estudiante: Modifique la aplicación de la automotora ( y que ha ido mejorando las últimas semanas) e implemente al menos un patrón. 
- Como es una mejora, debe utilizar el código generado anteriormente, modificando aquello que le resulte necesario para satisfacer estos requerimientos.


### Vehiculo
- `vehiculo` [ codigo, marca, tipo, modelo, año, kilometraje, patente ]
<br><br>
- `vehiculo` > `auto`
- `vehiculo` > `camioneta`
- `vehiculo` > `motocicleta`
<br><br>

### Venta
- `venta` > [ monto, fecha, código vehiculo, nombre, apellido, rut ]
<br><br>

### Listar vehiculos
- `TreeMap<Integer, Vehiculo> disponibles`
- `TreeMap<Integer, Vehiculo> vendidos`
<br><br>

#### PLAN:
- al crear un vehiculo se añade a la lista de vehiculos disponibles
- al vender un vehiculo de la lista de vehiculos disponibles debe quedar en la lista de vehiculos vendidos
- crear un hashset para almacenar los codigos usados
- al crear un vehiculo se genera el codigo y se añade a la lista de codigos usados