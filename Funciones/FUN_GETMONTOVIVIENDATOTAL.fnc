create or replace function FUN_GETMONTOVIVIENDATOTAL(pIdObra obra.idobra%type, pNroCertificado certipago.nrocertificado%type)
return itemcosto.costo%type is vMontoInfraTotal itemcosto.costo%type;
begin
  select SUM(fd.monto) into vMontoInfraTotal from fojadet fd
  inner join item i on (fd.idobra = i.idobra and fd.iditem = i.iditem)
  inner join certiobra co on (fd.idfoja = co.idfoja)
  where i.idtipoitem = 1 and co.idobra = pIdObra and co.nrocertificado = pNroCertificado;
  return vMontoInfraTotal;
end;
/
