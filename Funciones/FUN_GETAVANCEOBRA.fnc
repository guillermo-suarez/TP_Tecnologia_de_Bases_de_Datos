create or replace noneditionable function FUN_GETAVANCEOBRA(pNumObra obra.numobra%type)
return fojadet.avaactual%type
is vPorcAvanzado fojadet.avaactual%type;
   vIdObra obra.idobra%type;
   vCantFojas number(5);
begin
  vIdObra := fun_getIdObra(pNumObra);
  select count(*) into vCantFojas from foja f where f.idobra = vIdObra;
  if vCantFojas > 0 then
    select SUM(fun_getAvanceAportado(o.idobra, i.iditem)) into vPorcAvanzado from obra o
       inner join item i on i.idobra = o.idobra
       inner join fojadet fd on (fd.idobra = i.idobra and fd.iditem = i.iditem)
    where o.idobra = vIdObra
    and fd.idfoja = (select MAX(fd2.idfoja) from fojadet fd2 where fd2.idobra = o.idobra and fd2.iditem = i.iditem)
    group by o.numobra, o.idobra;
  else
    vPorcAvanzado := 0.0;
  end if;
  return vPorcAvanzado;
end;
/
