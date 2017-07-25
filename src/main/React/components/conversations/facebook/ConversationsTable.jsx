import React from 'react'
import PureRenderMixin from 'react-addons-pure-render-mixin'

export default class ConversationsTable extends React.Component {
    constructor(props) {
        super(props)
        this.shouldComponentUpdate = PureRenderMixin.shouldComponentUpdate.bind(this)
    }


    render() {
        console.log("uaaa")
        console.log(this.props.conversations)
        return  (
            <div>
                <h1>Inbox</h1>
                <table className="table table-stripped table-bordered">
                <thead>
                <tr>
                    <th>User</th>
                    <th>Message</th>
                </tr>
                </thead>
                <tbody>
                {
                    this.props.conversations.map(conversation =>
                        <tr key={conversation.get('id')}>
                            <td>
                                {conversation.get('senders').get(0).get('name')}
                            </td>
                            <td>
                                <a href={"#/conversations/" + conversation.get('id')}>
                                {conversation.get('conversation')}
                                {(conversation.get('unreadCount') > 0) ? "(" + conversation.get('unreadCount') + ")" : "" }
                                </a>
                            </td>
                        </tr>
                    )
                }

                </tbody>
            </table>
        </div>
        );
    }
}
