/* Dado un idObra y un idItem, devuelve el costo del item actual + los impuestos que correspondan */

create or replace function getCostoItemActual(idOb obra.idobra%type,
                              idIt item.iditem%type)
return itemcosto.costo%type
is
       costoItem itemcosto.costo%type;
begin
       select ((((ic.costo * (1 + o.porflete/100)) * (1 + o.porgastos/100)) * (1 + o.poruti/100)) *
       case when (i.idtipoitem = 1) then (1 + o.ivaviv/100)
            else (1 + o.ivainfra/100) end) into costoItem
       from itemcosto ic
              inner join item i on ic.iditem = i.iditem and ic.idobra = i.idobra
              inner join obra o on ic.idobra = o.idobra
       where o.idobra = idOb and i.iditem = idIt and ic.idredeterminacion =
       (select MAX(icc.idredeterminacion) from itemcosto icc
       where icc.iditem = idIt and icc.idobra = idOb);
return costoItem;
end;
