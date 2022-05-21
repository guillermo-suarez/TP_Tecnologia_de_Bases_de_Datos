create or replace noneditionable function FUN_GETTOTALITEMSBASICO(pIdObra obra.idobra%type)
return itemcosto.costo%type is vTotal itemcosto.costo%type;
begin
  select SUM(fun_getCostoItemBasico(o.idobra, i.iditem)) into vTotal from obra o
          inner join item i on i.idobra = o.idobra
  where o.idobra = pIdObra
  group by o.idobra;
  return vTotal;
end;
/
