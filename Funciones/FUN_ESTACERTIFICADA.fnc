create or replace noneditionable function FUN_ESTACERTIFICADA(pIdFoja foja.idfoja%type)
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
           inner join certiobra co on (co.idobra = cp.idobra and co.nrocertificado = cp.nrocertificado)
    where co.idfoja = pIdFoja;
    if vAbiertoCP = 0 then
      vResultado := 2;
    end if;
  end if;
  return vResultado;
end;
/
