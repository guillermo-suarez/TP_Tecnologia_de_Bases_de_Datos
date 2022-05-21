create or replace noneditionable function FUN_GETINCIDENCIA(pIdObra obra.idobra%type, pIdItem item.iditem%type)
return fojadet.avaactual%type is vIncidencia fojadet.avaactual%type;
begin
  vIncidencia := fun_getCostoItemBasico(pIdObra, pIdItem)*100/fun_getTotalItemsBasico(pIdObra);
  return vIncidencia;
end;
/
