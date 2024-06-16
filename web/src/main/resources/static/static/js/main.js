$(document).ready(function () {
    $('.editGameBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');

        $.get(href, function (game, status) {
            $('#editId').val(game.id);
            $('#editName').val(game.name);
            $('#editDescription').val(game.description);
        });
        $('#editGameModal').modal();
    });
    $('.deleteGameBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (game, status) {
            $('#deleteId').val(game.id);
        });
        $('#deleteGameModal').modal();
    });
    $('.startNewGameBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (game, status) {
            $('#newGameId').val(game.id);
        });
        $('#startNewGameModal').modal();
    });
    $('.assignUserRolesBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (userRole, status) {
            var rolesContainer = $('#rolesContainer');
            rolesContainer.empty();

            var roles = userRole.roles;
            var allRoles = [
                {role:"ADMIN", name:"adminRole"},
                {role:"USER", name:"userRole"},
                {role:"GAME_MASTER", name:"gameMasterRole"}
            ]
            allRoles.forEach(function (role) {
                var isChecked = roles.includes(role.role);
                var checkbox = $('<input>')
                    .attr('type', 'checkbox')
                    .addClass('form-check-input')
                    .attr('id', role.role)
                    .attr('name', 'roles')
                    .attr('value', role.role)
                    .prop('checked', isChecked);
                var label = $('<label>')
                    .attr('for', role.role)
                    .text(role.role)
                    .addClass('form-check-label');
                var div = $('<div>').addClass('form-check');
                div.append(checkbox).append(label);

                rolesContainer.append(div);
            });
            $('#userId').val(userRole.userId)
        });
        $('#assignUserRolesModal').modal();
    });
});
