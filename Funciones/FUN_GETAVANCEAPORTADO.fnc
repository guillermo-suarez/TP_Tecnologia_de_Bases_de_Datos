create or replace noneditionable function FUN_GETAVANCEAPORTADO(pIdObra obra.idobra%type, pIdItem item.iditem%type)
return fojadet.avaactual%type
is
  vAvAportado fojadet.avaactual%type;
begin
  vAvAportado := fun_getAvanceItem(pIdObra, pIdItem)*fun_getIncidencia(pIdObra, pIdItem)/100;
  return vAvAportado;
end;
/
