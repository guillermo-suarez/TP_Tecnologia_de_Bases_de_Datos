CREATE OR REPLACE NONEDITIONABLE PROCEDURE PRC_DIFMONTO (
       pNumObra IN obra.numobra%type,
       pFechaBusq IN redeterminacion.fechadesde%type,
       pError OUT number,
       vMontoBase OUT itemcosto.costo%type,
       vMontoRedet OUT itemcosto.costo%type
)
AS
       vIdObra obra.idobra%type;
       vIdRedet redeterminacion.idredeterminacion%type;

BEGIN
  SELECT fun_getidobra(pNumObra) INTO vIdObra FROM dual;
  IF vIdObra > 0 THEN --Existe una obra con el número de obra seleccionado
    vMontoBase := fun_gettotalitemsbasico(vIdObra);
    /*dbms_output.put_line('Numero de obra: '||pNumObra);
    dbms_output.put_line('Fecha buscada: '||pFechaBusq);
    dbms_output.put_line('Monto original: '||vMontoBase);*/
    vIdRedet := fun_consultafecharedet(vIdObra, pFechaBusq);
    IF vIdRedet > 0 THEN --Existe una redeterminacion en la fecha seleccionada
      vMontoRedet := fun_gettotalitemsredeterminado(vIdObra,vIdRedet);
      pError := 0;
      /*dbms_output.put_line('Monto redeterminado: '||vMontoRedet);
      dbms_output.put_line('Diferencia: '||vTotal);*/
    ELSE
      vMontoRedet := 0.0;
      pError := 2;
      /*dbms_output.put_line('La obra buscada no tiene una redeterminacion en dicha fecha');*/
    END IF;
  ELSE
    vMontoBase := 0.0;
    vMontoRedet := 0.0;
    pError := 1;        
    /* dbms_output.put_line('Numero de obra incorrecto'); */ 
  END IF;
END;
/
