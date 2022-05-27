/* A partir de un idFoja, esta funci�n retorna TRUE si ese idFoja aparece en al menos un CERTIOBRA
                                               FALSE si ese idFoja NO aparece en ning�n CERTIOBRA
Es decir, TRUE si la Foja con ese idFoja ya fue certificada y
          FALSE si la Foja con ese idFoja todav�a no fu� certificada */

create or replace noneditionable function FUN_ESTACERTIFICADA(pIdFoja foja.idfoja%type)
return boolean
is vResultado boolean;
   vConteo number(5);
begin
  vResultado := false;
  select count(*) into vConteo from certiobra co where co.idfoja = pIdFoja;
  if vConteo > 0 then
    vResultado := true;
  end if;
  return vResultado;
end;
/
