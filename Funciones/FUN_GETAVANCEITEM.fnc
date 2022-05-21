create or replace noneditionable function FUN_GETAVANCEITEM(pIdObra item.idobra%type, pIdItem item.iditem%type)
return fojadet.avaactual%type is vPorAvanzado fojadet.avaactual%type;
begin
  select ((case when (fd.avaacuanterior is null) then 0.0 else fd.avaacuanterior end)
  + (case when (fd.avaactual is null) then 0.0 else fd.avaactual end)) into vPorAvanzado from fojadet fd
  where fd.idobra = pIdObra and fd.iditem = pIdItem and fd.idfoja =
  (select MAX(fd.idfoja) from fojadet fd where fd.idobra = pIdObra and fd.iditem = pIdItem);
  return vPorAvanzado;
exception
  when no_data_found then
    vPorAvanzado := 0.0;
    return vPorAvanzado;
end;
/
