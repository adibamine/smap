import React from 'react'
import PureRenderMixin from 'react-addons-pure-render-mixin'

export default class UserCommentsTable extends React.Component {
    constructor(props) {
        super(props)
        this.shouldComponentUpdate = PureRenderMixin.shouldComponentUpdate.bind(this)
    }

    blockUser() {
        console.log(this.props.userId)
        this.props.block_user(this.props.userId)
    }


    render() {
        return  <div>
            <button onClick={this.blockUser.bind(this)}>Bloquer utilisateur</button>
                <table className="table table-stripped table-bordered">
            <thead>
            <tr>
                <th>User</th>
                <th>Commentaire</th>
                <th>Post</th>
            </tr>
            </thead>
            <tbody>
            {
                this.props.comments.map(comment =>
                    <tr key={comment.get('comment_id')}>
                        <td>
                            {comment.get('user_name')}
                        </td>
                        <td>
                            {comment.get('comment_text')}
                        </td>

                        <td>
                            <a target="_blank" href={"https://www.facebook.com/" + comment.get('id_post')}><strong>Post</strong></a>
                        </td>
                    </tr>
                )
            }

            </tbody>
        </table>

        </div>

    }
}
