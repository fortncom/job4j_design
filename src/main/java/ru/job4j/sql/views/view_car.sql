create view show_cars_where_navigator_or_passport_is_null as
select c.name as model, extract('year' from p.release_date) as date, p.weight as weight,e.name as engine,
b.name as body, t.name as transmission, w.radius,  n.name navigator, f.volume as "fuel volume"
from car c left join navigators n on c.navigator_id = n.id
left join fuel_tanks f on c.fuel_tank_id = f.id
left join wheels w on c.wheel_id = w.id
left join transmissions t on c.transmission_id = t.id
left join bodies b on c.body_id = b.id
left join engines e on c.engine_id = e.id
left join passports p on c.passport_id = p.id
where c.navigator_id is null or c.passport_id is null;


select * from show_cars_where_navigator_or_passport_is_null;