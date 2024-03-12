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
});
