create or replace function FUN_ESFOJEABLE(pIdObra obra.idobra%type)
return number
is vResultado number(1);
   vUltimoIdFoja foja.idfoja%type;
begin
  -- Checkeo el ID de la �ltima foja que tiene esa obra
  vUltimoIdFoja := fun_getultimoidfoja(pIdObra);
  -- Si no tiene ninguna...
  if vUltimoIdFoja = (-1) then
    vResultado := 1; -- La obra es fojabeable
  -- Si tiene una �ltima foja checkeamos el estado de esa �ltima...
  else
    -- Si esa �ltima foja esta certificada y cerrada...
    if fun_estacertificada(vUltimoIdFoja) = 2 then
      vResultado := 1; -- La obra es fojeable
    -- Si esa �ltima foja no esta certificada o est� certificada pero el certificado est� abierto...
    else
      vResultado := 0; -- La obra NO es fojeable
    end if;
  end if;
  return vResultado;
end;
/
