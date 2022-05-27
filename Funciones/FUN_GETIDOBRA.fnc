CREATE OR REPLACE FUNCTION FUN_GETIDOBRA(pNumObra obra.numobra%type)
return obra.idobra%type
is
vIdObra obra.idobra%type;
begin
  SELECT o.idobra into vIdObra FROM OBRA o WHERE o.numobra = pNumObra;
  return vIdObra;
exception
  when no_data_found then
    return -1;
end;
