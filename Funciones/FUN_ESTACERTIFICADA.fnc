/* A partir de un idFoja, esta función retorna 0 si ese idFoja NO aparece en ningún CERTIOBRA
                                               1 si ese idFoja aparece en al menos un CERTIOBRA
                                               2 si ese idFoja aparece en el menos un CERTIOBRA y, el CERTIPAGO que referencia esa CERTIOBRA, está cerrado
Es decir, 0 si la Foja con ese idFoja no fue certificada y
          1 si la Foja con ese idFoja fue certificada, pero ese certificado todavía no está cerrado
          2 si la Foja con ese idFoja fue certificada, y el certificado ya se cerró */

create or replace function FUN_ESTACERTIFICADA(pIdFoja foja.idfoja%type)
return number
is vResultado number(1);
   vConteo number(5);
   vAbiertoCP certipago.abierto%type;
begin
  vResultado := 0;
  select count(*) into vConteo from certiobra co where co.idfoja = pIdFoja;
  if vConteo > 0 then
    vResultado := 1;
    select cp.abierto into vAbiertoCP from certipago cp
    inner join certiobra co on (co.idobra = cp.idobra and co.nrocertificado = cp.nrocertificado);
    if vAbiertoCP = 0 then
      vResultado := 2;
    end if;
  end if;
  return vResultado;
end;
