--NO ANDA

--CREAR FOJA VACÍA

CREATE OR REPLACE PROCEDURE PRO_CrearFoja (pNumObra obra.numobra%type)
is
vIdObra obra.idobra%type; --id de la obra en la que estamos trabajando
vIdFojaAnterior foja.idfoja%type; --id de la foja anterior
vAux foja.idfoja%type; --Ultimo id del cerifiobra.
vIdFojaCerti foja.idfoja%type; --id de la ultima foja certificada
vIdFojaActual foja.idfoja%type; --id de la foja que estaremos creando
vAvance fojadet.avaacuanterior%type; --avance acumulado anterior
begin
  --Recuperar el idObra segun el numObra
  SELECT FUN_RecuperarIdObra(pNumObra) into vIdObra from DUAL; 
  --Recuperamos la ultima foja de la obra
  SELECT MAX(idfoja) into vIdFojaAnterior FROM FOJA f WHERE idobra=vIdObra; 
  --Recuperamos el id de la ultima certiobra
  SELECT MAX(nrocertificado) into vAux FROM CertiObra WHERE idobra=vIdObra;
  --Recuperamos la ultima foja certificada
  SELECT idfoja into vIdFojaCerti from CERTIOBRA WHERE certiobra.idfoja=vIdFojaAnterior;
  --Si la ultima foja está certificada
  IF vIdFojaAnterior = vIdFojaCerti THEN
   --INSERTAMOS Una foja con valores idfoja=NULL (consultar si esta bien), idobra actual, sin fecha.
   INSERT INTO FOJA
   VALUES(NULL,vIdObra,NULL);
   --Recuperamos el idfoja de nuestra foja otorgado por la secuencia
   SELECT MAX(idfoja) into vIdFojaActual FROM FOJA WHERE foja.idobra=vIdObra;
   --Declaramos un cursos para poder iterar los detalles. Itera iditem
   DECLARE CURSOR detallesFoja is
   SELECT iditem FROM ITEM where item.idobra=vIdObra;
   --Creamos la fila del cursor
   filaDetalles detallesFoja%rowtype;
            --Preguntar por qué era begin
            BEGIN 
              --abrimos el cursor
              open detallesFoja;
              loop
                fetch detallesFoja into filaDetalles; --Asignamos valores en la fila
                exit when detallesFoja%notfound; --Condicion de salida
                  --Asignmos el avance acumulado en vAvance.
                  SELECT avaacuanterior + avaactual into vAvance FROM FOJADET
                  WHERE idfoja = vIdFojaAnterior AND iditem =filaDetalles.iditem;
                  --Insertamos el detalle con los siguientes valores:
                  --IdFoja, idItem, idObra, avanceAcumulado, NULL avance actual, NULL monto
                  INSERT INTO FOJADET
                  VALUES (vIdFojaActual, filaDetalles.iditem, vIdObra, vAvance, NULL, NULL);
              end loop;
              close detallesFoja; --cerramos el cursor
             END;
    END IF;     
end;


