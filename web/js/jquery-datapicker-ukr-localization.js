/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function () {
    $.datepicker.regional['ukr'] = {clearText: 'Очистити', clearStatus: '',
        closeText: 'Закрити', closeStatus: 'Закрити без змін',
        prevText: 'Попередній', prevStatus: 'Переглянути попередній місяць',
        nextText: 'Наступний', nextStatus: 'Переглянути наступний місяць',
        currentText: 'Поточний', currentStatus: 'Поточний місяць',
        monthNames: ['Січень','Лютий','Березень','Квітень','Травень','Червень',
        'Липень','Серпень','Вересень','Жовтень','Листопад','Грудень'],
        monthNamesShort: ['Січ','Лют','Бер','Квіт','Трав','Черв',
        'Лип','Серп','Вер','Жов','Лист','Груд'],
        monthStatus: 'Переглянути ще один місяць', yearStatus: 'Переглянути ще один рік',
        weekHeader: 'Sm', weekStatus: '',
        dayNames: ['Неділя','Понеділок','Вівторок','Середа','Четвер','Пятниця','Субота'],
        dayNamesShort: ['Нед','Пон','Вів','Сер','Чет','Пят','Суб'],
        dayNamesMin: ['Нд','Пн','Ві','Ср','Чт','Пт','Сб'],
        dayStatus: 'Використання DD у якості першого дня тижня', dateStatus: 'Оберіть DD, MM',
        dateFormat: 'dd.mm.yy', firstDay: 1, 
        initStatus: 'Оберіть дату', isRTL: false};
     $.datepicker.setDefaults($.datepicker.regional['ukr']);
 });
