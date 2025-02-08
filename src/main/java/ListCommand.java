public class ListCommand extends Command{

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MonaException {
        System.out.println("Alright Joker, here is what you need to do:");
        if (tasks.getTasks().isEmpty()) {
            System.out.println("Waittt, you didn't tell me anything!!");
        } else{
            for (int i = 1; i <= tasks.getSize(); i++) {
                System.out.println(i + ": " + tasks.getTask(i - 1));
            }
        }
    }

}
