CREATE OR REPLACE NONEDITIONABLE FUNCTION FUN_FOJAESTAVACIA (pIdFoja foja.idfoja%type)
return number
is
vEs number := 0;
vAvance fojadet.avaactual%type;
begin
  --Declaramos un cursos para poder iterar los detalles. Itera id detalle foja
  declare cursor detallesFoja IS
   SELECT f.iditem FROM FojaDet f WHERE f.idfoja=pIdFoja;
    --Creamos la fila del cursor
   filaDetalles detallesFoja%rowtype;
   --abrimos el cursor
   BEGIN
      open detallesFoja;
      loop
        fetch detallesFoja into filaDetalles; --Asignamos valores en la fila
        exit when detallesFoja%notfound; --Condicion de salida
            SELECT f.avaactual into vAvance FROM Fojadet F 
            WHERE f.idfoja=pIdFoja and f.iditem=filaDetalles.iditem;
            if vAvance is null then
              vEs := 1;
            end if;
        end loop;
      close detallesFoja; --cerramos el cursor
  END;
  return vEs;
END;
/
