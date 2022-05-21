create or replace noneditionable function FUN_GETCOSTOITEMBASICO(pIdObra obra.idobra%type,
                              pIdItem item.iditem%type)
return itemcosto.costo%type
is
       vCostoItem itemcosto.costo%type;
begin
       select ((((ic.costo * (1 + o.porflete/100)) * (1 + o.porgastos/100)) * (1 + o.poruti/100)) *
       case when (i.idtipoitem = 1) then (1 + o.ivaviv/100)
            else (1 + o.ivainfra/100) end) into vCostoItem
       from itemcosto ic
              inner join item i on ic.iditem = i.iditem and ic.idobra = i.idobra
              inner join obra o on ic.idobra = o.idobra
       where o.idobra = pIdObra and i.iditem = pIdItem and ic.idredeterminacion = 0;
return vCostoItem;
end;
/
