create or replace noneditionable procedure PRC_UPDATEFOJADET(pIdObra obra.idobra%type,
                                              pIdItem item.iditem%type,
                                              pPorcAvanz fojadet.avaactual%type,
                                              pError OUT number)
is
  vUltimoIdFoja foja.idfoja%type;
  vConteo number(1);
  vAvaAcuAnterior fojadet.avaacuanterior%type;
begin
  if pPorcAvanz >= 0 then
    vUltimoIdFoja := fun_getultimoidfoja(pIdObra);
    if vUltimoIdFoja >= 0 then /* Si existe una última FOJA de esa OBRA... */
      if (fun_estacertificada(vUltimoIdFoja) = 0) then /* ... y esa FOJA no está certificada... */
        select count(*) into vConteo from fojadet fd where (fd.idfoja = vUltimoIdFoja and fd.iditem = pIdItem);
        if vConteo = 1 then /* Si esa FOJA tiene exactamente una FOJADET de ese ITEM... */
          select fd.avaacuanterior into vAvaAcuAnterior from fojadet fd where (fd.idfoja = vUltimoIdFoja and fd.iditem = pIdItem);
          if ((vAvaAcuAnterior + pPorcAvanz) <= 100.00) then
            update fojadet fd
            set fd.avaactual = pPorcAvanz
            where fd.idfoja = vUltimoIdFoja and fd.iditem = pIdItem;
            pError := 0; /* El update se hizo correctamente */
          else
            pError := 5; /* El porcentaje que se está tratando de colocar representaría que el ITEM estaría ahora en más del 100% */
          end if;
        else
          pError := 4; /* No existe FOJADET de la última FOJA que haga referencia a ese ITEM,
                          por lo tanto, ese ITEM no existe en esa OBRA */
        end if;

      else
        pError := 3; /* Se esta intentando cargar una FOJADET de una FOJA que ya está certificada
                                                              o que ya esta certificada y cerrada*/
      end if;
    else
      pError := 2; /* La obra no tiene fojas, no tiene sentido intentar cargar avance */
    end if;
  else
    pError := 1; /* Se esta intentando cargar un porcentaje de avance negativo */
  end if;
end;
/
