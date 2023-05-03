# API

## Эндпоинты
1. CRUDS (create, read, update, delete, search)

## Описание сущности Training
1. trainingId - id сущности тренировки
2. dateTime - дата и время тренировки
3. clientId - id объекта клиента
4. paymentId - id объекта оплаты
5. trainingComplexIds - список id объектов комплекса упражнений
6. load - нагрузка на клиента

## Описание сущности Client
1. clientId - id сущности клинета
2. phone - номер телефона
3. mail - електронка клиента
4. goal - цель клиента (что клиент хочет от тренировок)
5. weight - вес клиента

## Описание сущности Payment
1. id - id сущности оплаты
2. amount - сумма оплаты
3. credit - задолженность
4. isPaid - оплачено

## Описание сущности TrainingComplex
1. id - id сущности 
2. trainingName - наименование упражнения
3. load - нагрузка упражнения (ккал)
4. time - время выполнения