const replyInput = document.getElementById("replyInput");

if (replyInput) {
   replyInput.addEventListener("keydown", function(e) {
      if (e.key === "Enter") {
         e.target.form.submit();
      }
   });
}