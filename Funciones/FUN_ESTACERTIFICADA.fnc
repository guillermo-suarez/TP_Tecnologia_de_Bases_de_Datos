/* A partir de un idFoja, esta función retorna TRUE si ese idFoja aparece en al menos un CERTIOBRA
                                               FALSE si ese idFoja NO aparece en ningún CERTIOBRA
Es decir, TRUE si la Foja con ese idFoja ya fue certificada y
          FALSE si la Foja con ese idFoja todavía no fué certificada */

create or replace function FUN_ESTACERTIFICADA(pIdFoja foja.idfoja%type)
return number
is vResultado number(1);
   vIdObraCO obra.idobra%type;
   vNroCertif certipago.nrocertificado%type;
   vConteo number(5);
   vAbiertoCP certipago.abierto%type;
begin
  vResultado := 0;
  select co.idobra, co.nrocertificado, count(*)
  into vIdObraCO, vNroCertif, vConteo from certiobra co where co.idfoja = pIdFoja;
  if vConteo > 0 then
    vResultado := 1;
    select cp.abierto into vAbiertoCP from certipago cp
    where (cp.idobra = vIdObraCO and cp.nrocertificado = vNroCertif);
    if vAbiertoCP = 0 then
      vResultado := 2;
    end if;
  end if;
  return vResultado;
end;
