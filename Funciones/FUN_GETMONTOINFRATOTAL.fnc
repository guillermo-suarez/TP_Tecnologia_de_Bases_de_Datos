create or replace noneditionable function FUN_GETMONTOINFRATOTAL(pIdObra obra.idobra%type, pNroCertificado certipago.nrocertificado%type)
return itemcosto.costo%type 
is vMontoInfraTotal itemcosto.costo%type;
 vConteo number;
begin
  select count(*) into vConteo from fojadet fd
  inner join item i on (fd.idobra = i.idobra and fd.iditem = i.iditem)
  inner join certiobra co on (fd.idfoja = co.idfoja)
  where i.idtipoitem = 2 and co.idobra = pIdObra and co.nrocertificado = pNroCertificado;
  if vConteo > 0 then
    select SUM(fd.monto) into vMontoInfraTotal from fojadet fd
    inner join item i on (fd.idobra = i.idobra and fd.iditem = i.iditem)
    inner join certiobra co on (fd.idfoja = co.idfoja)
    where i.idtipoitem = 2 and co.idobra = pIdObra and co.nrocertificado = pNroCertificado;
  else
    vMontoInfraTotal := 0.0;
  end if;
  
  return vMontoInfraTotal;
end;
/
